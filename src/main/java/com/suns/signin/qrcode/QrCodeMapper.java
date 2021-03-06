package com.suns.signin.qrcode;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by sunshuai on 2019/5/22
 */
@Mapper
public interface QrCodeMapper {

    @Insert("insert into tb_qrcode(description,teacher) values(#{description},#{teacher})")
    @Options(useGeneratedKeys = true, keyProperty = "serial", keyColumn = "serial")
    Integer addSign(QrCode qrCode);


    @Select("select * from tb_qrcode where teacher=#{teacher}")
    List<QrCode> getSign(@Param("teacher") String teacher);

    @Delete("delete from tb_qrcode where serial=#{serial}")
    Integer deleteSign(@Param("serial") String serial);

    @Update("update tb_qrcode set description=#{description} where serial=#{serial}")
    Integer updateSign(QrCode qrCode);

    @Insert("insert into tb_qrcode_has_student(qrcode_serial,stu_id,stu_name,stu_openid,state) values(#{qrcodeSerial},#{stuId},#{stuName},#{stuOpenId},#{state}) ")
    Integer addSignStudent(SignStudent signStudent);

    @Select("select * from tb_qrcode_has_student where qrcode_serial=#{serial}")
    List<SignStudent> getSignStudent(@Param("serial") String serial);

    @Update("update tb_qrcode_has_student set qrcode_serial=#{serial},stu_id=#{stuId},stu_openid=#{openId},state=#{state} where id=#{id}")
    Integer updateSignStudent(@Param("id") String id, @Param("serial") String serial, @Param("stuId") String stuId, @Param("openId") String openId, @Param("state") String state);

    @Update("update tb_qrcode_has_student set stu_openid=#{openId},state=#{state} where stu_id=#{stuId} and qrcode_serial=#{serial}")
    Integer signIn(@Param("serial") String serial, @Param("stuId") String stuId, @Param("openId") String openId, @Param("state") String state);


}
