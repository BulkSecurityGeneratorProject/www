package com.test.second.service.impl;

import com.test.second.mapper.JobMapper;
import com.test.second.service.JobService;
import com.test.second.domain.Job;
import com.test.second.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Job}.
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }



    /**
     * Save a job.
     *
     * @param job the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Job save(Job job) {
        log.debug("Request to save Job : {}", job);
        return jobRepository.save(job);
    }

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Job> findAll(Pageable pageable) {
        log.debug("Request to get all Jobs");
        return jobRepository.findAll(pageable);
    }

    /**
     * Get all the jobs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Job> findAllWithEagerRelationships(Pageable pageable) {
        return jobRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one job by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Job> findOne(Long id) {
        log.debug("Request to get Job : {}", id);
        return jobRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the job by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Job : {}", id);
        jobRepository.deleteById(id);
    }
}
