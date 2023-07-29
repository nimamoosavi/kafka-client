package app.ladderproject.kafka.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class JsonSerialization implements Serializer<String> {
    private String encoding = "UTF8";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JsonSerialization(){

    }

    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null) {
            encodingValue = configs.get("serializer.encoding");
        }

        if (encodingValue instanceof String) {
            this.encoding = (String)encodingValue;
        }

    }

    public byte[] serialize(String topic, String data) {
        try {
            return data == null ? null : data.getBytes(this.encoding);
        } catch (UnsupportedEncodingException var4) {
            throw new SerializationException("Error when serializing string to byte[] due to unsupported encoding " + this.encoding);
        }
    }

}
