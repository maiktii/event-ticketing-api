package com.example.boilerplate_java_springboot.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotNull
    @JsonProperty("event_id")
    private int eventID;

    @NotBlank
    @JsonProperty("name_order")
    private String nameOrder;

    @NotBlank
    @Email
    @JsonProperty("email_order")
    private String emailOrder;

    @NotBlank
    private String orderStatus;

    @DecimalMin("0.00")
    @JsonProperty("total_amount")
    private double totalAmount;

}
