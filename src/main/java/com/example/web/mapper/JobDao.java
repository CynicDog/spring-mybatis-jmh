package com.example.web.mapper;

import com.example.web.vo.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface JobDao {

    List<Job> getAllJobs();

    Job getJobById(@Param("job_id") String id);
}
