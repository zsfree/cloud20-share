package com.cy.springcloud.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UploadConf
 * @Description TODO
 * @Author zs
 * @Date 2020/4/28 10:50
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadConf  implements Serializable
{
    private String base_url;
    private String save_url;
}
