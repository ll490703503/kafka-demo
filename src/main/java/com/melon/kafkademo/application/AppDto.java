package com.melon.kafkademo.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDto {

    @NotBlank(message = "envType must not be null")
    private String envType;
    @NotBlank(message = "envName must not be null")
    private String envName;
    @NotBlank(message = "appName must not be null")
    private String appName;
    @NotBlank(message = "version must not be null")
    private String version;
    private String needDownload = "true";

}
