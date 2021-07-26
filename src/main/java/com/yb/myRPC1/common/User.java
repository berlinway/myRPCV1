package com.yb.myRPC1.common;

import lombok.*;

import java.io.Serializable;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  implements Serializable {
    private Integer id;
    private String userName;
    private boolean sex;
}
