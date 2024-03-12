package hgc.demojwt.responses;

import hgc.demojwt.entitys.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDTO {
	private String token;
	private Category category;
}
