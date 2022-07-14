package fitIn.fitInserver.dto;


import fitIn.fitInserver.domain.News;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto {

    private String press;

    private String title;

    private String content;

    private String category;

    private String image_url;

    private String link;

    public News toEntity(){
        return News.builder()
                .press(press)
                .title(title)
                .content(content)
                .category(category)
                .image_url(image_url)
                .link(link)
                .build();
    }
}