package com.neuedu.lab.model.mapper;

import com.neuedu.lab.model.po.Template;
import com.neuedu.lab.model.po.TemplateContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemplateMapper {
    void addTemplate(Template template);
    void deleteTemplate(Integer template_id);
    void updateTemplate(Template template);

    List<Template> getAllTemplates(@Param("doctor_id")Integer doctor_id, @Param("department_id")String department_id);
    List<Template> getTemplatesByDoctor(Integer doctor_id);
    List<Template> getTemplatesByDepartment(String department_id);

    void addTemplateContentNonMedicine(TemplateContent templateContent);
    void addTemplateContentMedicine(TemplateContent templateContent);
    void deleteTemplateContent(Integer template_content_id);
    List<TemplateContent> getTemplateContentNonMedicine(Integer template_connect_id);
    List<TemplateContent> getTemplateContentMedicine(Integer template_id);
}
