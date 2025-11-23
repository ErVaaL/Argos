package com.erval.argos.core.domain.alert;

import java.time.Instant;

public record Alert(String id, String ruleId,String deviceId, String description, Instant timestamp){
}
