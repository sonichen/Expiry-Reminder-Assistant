package com.cyj.utils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TokenResult {
    private String access_token;
    private String expires_in;
}
