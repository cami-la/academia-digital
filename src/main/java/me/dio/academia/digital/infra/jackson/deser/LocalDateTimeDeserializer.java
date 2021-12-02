package me.dio.academia.digital.infra.jackson.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Venilton Falvo Jr
 */
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = -7209271947629593913L;

	public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        return LocalDateTime.parse(parser.readValueAs(String.class), JavaTimeUtils.LOCAL_DATE_TIME_FORMATTER);
    }
}
