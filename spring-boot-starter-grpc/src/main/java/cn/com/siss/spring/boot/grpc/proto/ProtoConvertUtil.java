package cn.com.siss.spring.boot.grpc.proto;

import cn.com.siss.spring.boot.grpc.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

@Slf4j
public class ProtoConvertUtil {

    public static String domainToVoProto(String className) {
        Class<?> c = null;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        String fieldName = null;
        int orderId = 0;
        String type = null;
        Field[] fields = null;
        try {
            c = Class.forName(className);
            fields = c.getDeclaredFields();
            sb = sb.append("message " + c.getSimpleName() + "DTO" + " {" + "\n");
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFound", e);
        }
        for (Field fd : fields) {
            fieldName = fd.getName();
            orderId = ++orderId;
            type = fd.getType().getTypeName();
            if ("java.lang.Integer".equals(type)) {
                type = "Integer";
            }
            if ("java.lang.String".equals(type)) {
                type = "string";
            }
            if ("java.lang.Long".equals(type)) {
                type = "Long";
            }
            sb = sb.append("    " + type + " " + fieldName + " = " + orderId + " ;" + "\n");
            sb1 = sb1.append(fieldName + ";");
        }
        sb = sb.append(" }" + "\n");
        log.info("sb1 " + sb1.toString());
        return sb.toString();
    }

    /**
     * 生成除了dto之外的request,response,rpc service
     * @param seviceName
     * @return
     */
    public static String popularProto(String seviceName) {
        StringBuilder sb = null;
        String dtoName = seviceName + "DTO ";
        String requestName = seviceName + "Request ";

        String responseName = seviceName + "Response ";
        String listResponseName = seviceName + "ListResponse ";
        if (StringUtil.isNotEmpty(seviceName)) {
            sb = new StringBuilder();
            //添加request
            sb = sb.append("message " + requestName + " {\n").append("    int64 id = 1;\n" +
                    "    Long appId = 2;\n" +
                    "    int32 pageNumber = 5;\n" +
                    "    int32 pageSize = 6;\n" +
                    "}\n");
            //添加response
            sb = sb.append("message " + responseName + " {" + "\n").append("    int32 returnCode = 1;\n" +
                    "    string message = 2;\n" + "    " + (dtoName + StringUtils.lowerCase(seviceName) + "DTO") +
                    " = 3;\n" +
                    "}\n");
            //添加listResponse
            sb = sb.append("message " + listResponseName + " {\n").append("    int32 returnCode = 1;\n" +
                    "    string message = 2;\n" +
                    "    repeated " + (dtoName + StringUtils.lowerCase(seviceName) + "DTOList") + " = 3;\n" +
                    "    int32 pageNumber = 4;\n" +
                    "    int32 pageSize = 5;\n" +
                    "    int32 totalRecord = 6;\n" +
                    "}\n");
            //添加rpc service接口
            sb = sb.append("service " + (seviceName + "Service") + " {\n" +
                    "    rpc add (" + dtoName + ") returns (" + responseName + ") {\n" +
                    "    }\n" +
                    "    rpc query (" + dtoName + ") returns (" + responseName + ") {\n" +
                    "    }\n" +
                    "    rpc update (" + dtoName + ") returns (" + responseName + ") {\n" +
                    "    }\n" +
                    "    rpc delete (" + dtoName + ") returns (" + responseName + ") {\n" +
                    "    }\n" +
                    "    rpc list (" + requestName + ") returns (" + listResponseName + ") {\n" +
                    "    }\n" +
                    "}\n");
        }

        return sb.toString();
    }

    /**
     * @param java_package
     * @param baseDomainPackage
     * @param services
     */
    public static void popularProtoFile(String java_package, String baseDomainPackage, String[] services) {
        String outer_classname = "";
        String protoFile = "";
        String src = "";
        String dest = "";
        StringBuilder sb = new StringBuilder();
        String filename = "";
        BufferedWriter writer = null;
        for (String service : services) {
            src = baseDomainPackage + service;
            dest = ProtoConvertUtil.domainToVoProto(src);
            protoFile = ProtoConvertUtil.popularProto(service);
            sb = sb.append(dest + "\n");
            outer_classname = service + "Proto";
            filename = "src/main/proto/" + StringUtils.lowerCase(service) + ".proto";
            String header = "syntax = \"proto3\";\n" + "import \"type.proto\";\n" +
                    "\n" +
                    "option java_package = " + java_package + ";\n" +
                    "option java_outer_classname = \"" + outer_classname + "\";" +
                    "\n";
            try {
                writer = new BufferedWriter(new FileWriter(new File(filename)));
                writer.append(header);
                writer.newLine();
                writer.append(dest);
                writer.append(protoFile);
            } catch (Exception e) {
                log.error("error is ", e);
            } finally {
                if (null != writer) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        log.error("error is ", e);
                    }
                }
            }
        }
        //生成type.proto
        try {
            filename = "src/main/proto/type.proto";
            writer = new BufferedWriter(new FileWriter(new File(filename)));
            writer.append("syntax = \"proto3\";\n" +
                    "\n" +
                    "option java_package = \"cn.vpclub.protobuf\";\n" +
                    "option java_outer_classname = \"TypeProto\";\n" +
                    "\n" +
                    "message Integer{\n" +
                    "    int32 value = 1;\n" +
                    "}\n" +
                    "\n" +
                    "message Long{\n" +
                    "    int64 value = 1;\n" +
                    "}");
            writer.newLine();
        } catch (Exception e) {
            log.error("error is ", e);
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("error is ", e);
                }
            }
        }
    }

    public static void test(String[] args) {
        String msg = ProtoConvertUtil.domainToVoProto("cn.vpclub.moses.merchants.provider.storage.domain.Contract");
        log.info(msg);
    }

}
