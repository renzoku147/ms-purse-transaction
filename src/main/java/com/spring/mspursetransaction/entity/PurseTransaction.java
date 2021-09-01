package com.spring.mspursetransaction.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.kafka.common.header.Headers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document("PurseTransaction")
@AllArgsConstructor
@NoArgsConstructor
public class PurseTransaction implements Serializable{
	String id;
	Integer numberOrigin;
	Integer numberDetiny;
	Double balance;
	
	public byte[] serialize(String s, Object o) {
        return new byte[0];
    }

    public byte[] serialize(String topic, Headers headers, Object data) {
        return new byte[0];
    }
}
