package com.xin.demo.mapper;

import com.xin.demo.bean.People;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-05 17:06
 * @github https://github.com/xyf527
 * @copyright
 */

@Repository
public interface PeopleMapper {

    List<People> getPeopleList();

}
