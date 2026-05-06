package com.xin.demo.service;

import com.xin.demo.bean.People;
import com.xin.demo.mapper.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-05 17:06
 * @github https://github.com/xyf527
 * @copyright
 */

@Service
public class PeopleService {

    @Autowired
    PeopleMapper peopleMapper;

    public List<People> getPeopleList() {
        return peopleMapper.getPeopleList();
    }

}
