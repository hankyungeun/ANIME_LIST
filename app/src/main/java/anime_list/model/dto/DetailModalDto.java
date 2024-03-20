package anime_list.model.dto;

import java.util.ArrayList;
import java.util.List;

import anime_list.model.vo.AniList;
import anime_list.model.vo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailModalDto {

    private AniList aniDetail;
    private List<Comment> commentList;
    
}
