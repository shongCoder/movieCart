package org.example.mf.movie;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieVO {

    private Integer mid;
    private String mtitle;
    private String imgUrl;

}
