package com.folijet.columbia.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.folijet.columbia.core.metadata.EntityObjectyImpl;

import java.io.IOException;

@Deprecated
public class EventPayloadSerializer extends StdSerializer<EntityObjectyImpl> {
    protected EventPayloadSerializer(Class<EntityObjectyImpl> t) {
        super(t);
    }

    protected EventPayloadSerializer(JavaType type) {
        super(type);
    }

    protected EventPayloadSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected EventPayloadSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(EntityObjectyImpl value, JsonGenerator gen, SerializerProvider provider) throws IOException {

    }
}
