package com.shopleech.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDBTable(tableName = "sl_contact")
public class Contact {
    @DynamoDBAutoGeneratedKey
    UUID id;
    @DynamoDBAttribute
    UUID userId;
    @DynamoDBAttribute
    String name;
    @DynamoDBAttribute
    String value;
    @DynamoDBAttribute
    LocalDateTime createdAt;
    @DynamoDBAttribute
    String createdBy;
    @DynamoDBAttribute
    LocalDateTime modifiedAt;
    @DynamoDBAttribute
    String modifiedBy;
}