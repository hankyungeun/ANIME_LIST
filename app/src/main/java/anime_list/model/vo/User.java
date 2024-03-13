package anime_list.model.vo;

import org.checkerframework.common.returnsreceiver.qual.This;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@This
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private String userPk;
    private String userId;
    private String passwd;
    private String name;


    
}
    
