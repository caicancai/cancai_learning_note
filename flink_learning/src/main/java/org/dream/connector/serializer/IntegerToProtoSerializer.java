package org.dream.connector.serializer;

import com.google.cloud.flink.bigquery.sink.exceptions.BigQuerySerializationException;
import com.google.cloud.flink.bigquery.sink.serializer.BigQueryProtoSerializer;
import com.google.protobuf.ByteString;
import com.google.protobuf.Int32Value;

public class IntegerToProtoSerializer extends BigQueryProtoSerializer {
    @Override
    public ByteString serialize(Object record) throws BigQuerySerializationException {
        return Int32Value.of((Integer) record).toByteString();
    }
}
