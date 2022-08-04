package com.jobeth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA in 2022/8/4 19:51
 *
 * @author Jobeth
 * Description: -
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private String buyerName;
    private String address;
    private Date date;
}
