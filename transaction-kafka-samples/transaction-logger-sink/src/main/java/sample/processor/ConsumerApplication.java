/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.processor;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.TopicProcessor;

/**
 * @author Bjarte Stien Karlsen
 */
@SpringBootApplication
public class ConsumerApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @SuppressWarnings("deprecation")
	private final TopicProcessor<PersonEvent> processor = TopicProcessor.create();

    @Bean
    public Consumer<List<PersonEvent>> process() {
    	
    	return pes -> {
    		for(PersonEvent ps : pes) {
    			logger.info("{}",ps);
    		}
    	};
    	
//        return pe -> logger.info("{}", pe);
    }


    static class PersonEvent {

        String name;
        String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", PersonEvent.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type='" + type + "'")
                .toString();
        }
    }
}