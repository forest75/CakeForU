package com.a604.cake4u.imagefile.dto;

import com.a604.cake4u.enums.EImageFileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageFileDto {
    private String origImageFileName;
    private String imageFileUri;
    private EImageFileType imageFileType;
}
