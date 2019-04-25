package cn.com.siss.spring.boot.grpc.utils;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MessageOrBuilder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GRpcPropertyUtils {
    public static void setField(GeneratedMessageV3.Builder builder, Descriptors.FieldDescriptor field, Object value) {
        try {
            log.trace("Set gRpc field: {}, value: {}", field.getName(), value);
            builder.setField(field, value);
        } catch (Exception e) {
            log.trace("Set gRpc field exception: {}", e);
        }
    }

    public static void setRepeatedField(GeneratedMessageV3.Builder builder, Descriptors.FieldDescriptor field, Object value) {
        try {
            log.trace("Set gRpc repeated field: {}, value: {}", field.getName(), value);
            builder.addRepeatedField(field, value);
        } catch (Exception e) {
            log.trace("Set gRpc field exception: {}", e);
        }
    }

    public static MessageOrBuilder getFieldBuilder(GeneratedMessageV3.Builder builder, Descriptors.FieldDescriptor field) {
        try {
            log.trace("Get gRpc field builder: {}", field.getName());
            return builder.newBuilderForField(field);
        } catch (Exception e) {
            log.trace("Set gRpc field exception: {}", e);
        }
        return null;
    }
}
