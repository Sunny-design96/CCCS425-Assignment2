package com.assignment1;

import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationModule;
import com.github.victools.jsonschema.generator.OptionPreset;


public class StudentSchemaGenerator {
	public static void main(String[] args) {
		try {
			ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(om,
				    SchemaVersion.DRAFT_7,
				    OptionPreset.PLAIN_JSON
				);

				// Add Jakarta module manually
				configBuilder.with(new JakartaValidationModule());

				// Optional: inline and definitions
				configBuilder.with(Option.DEFINITIONS_FOR_ALL_OBJECTS);
				configBuilder.with(Option.INLINE_ALL_SCHEMAS);

			SchemaGenerator generator = new SchemaGenerator(configBuilder.build());
			JsonNode jsonSchema = generator.generateSchema(Student.class);
			((com.fasterxml.jackson.databind.node.ObjectNode) jsonSchema).put("title", "Student");
			
			File schema = Paths.get("src/main/resources/student_schema.json").toFile();
			om.writeValue(schema, jsonSchema);
			
			System.out.println("Student schema json generated");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
