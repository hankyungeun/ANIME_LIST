package anime_list.model.vo;

import org.checkerframework.common.returnsreceiver.qual.This;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@This
@Setter
@Getter
@ToString
@Data
public class User {
    private String userPk;
    private String userId;
    private String passwd;
    private String name;
}
