package br.com.fiap.studentcard.batch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import br.com.fiap.studentcard.models.Student;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    
	Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

	@Bean
	public ItemReader<Student> itemReader(){
		return new FlatFileItemReaderBuilder<Student>()
			.name("crypto file reader")
			.resource(new FileSystemResource("src/main/resources/lista_alunos.txt"))
			.lineTokenizer(new FixedLengthTokenizer()  {{
				setNames("name", "card_Number", "account_number");
				setColumns(new Range(1,41), new Range(42,49), new Range(50,55));
				setStrict(false);
		 	}})
			.fieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {{ setTargetType(Student.class); }})
			.build();
	}

	@Bean
	public ItemProcessor<Student, Student> itemprocessor() {
		return student -> {
			if(!student.getName().contains("--") && 
				student.getName().length() > 0)
			{
				logger.info("[PROCESSOR] - student {}", student.getName());
				student.setName(student.getName());
				student.setAccountNumber(student.getAccountNumber());
				student.setCardNumber(student.getCardNumber());
				return student;
			}
			else
			{
				return null;
			}
		};
	}

	@Bean
	public ItemWriter<Student> itemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Student>()
			.sql("insert into STUDENT(name, card_number, account_number) values(:name, :cardNumber, :accountNumber)")
			.dataSource(dataSource)
			.beanMapped()
			.build();
	}

	@Bean
	public Step step(
		StepBuilderFactory stepBuilderFactory,
		ItemReader<Student> itemReader,
		ItemProcessor<Student, Student> itemProcessor,
		ItemWriter<Student> itemWriter
	)
	{
		return stepBuilderFactory.get("step chunk process txt")
			.<Student, Student>chunk(3)
			.reader(itemReader)
			.processor(itemProcessor)
			.writer(itemWriter)
			.allowStartIfComplete(true)
			.build();
	}

	@Bean
	public Job job(
		JobBuilderFactory jobBuilderFactory,
		Step step) {
			return jobBuilderFactory
			.get("job process file")
			.start(step)
			.build();
	}
}
