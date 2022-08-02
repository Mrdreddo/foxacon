package com.foxacons.base.repos;
import org.springframework.data.repository.CrudRepository;

import com.foxacons.base.entity.ProjectDetails;
public interface ProjectDetailsDao extends CrudRepository<ProjectDetails, Integer>  {
	public ProjectDetails findByProjectId(int id);
}
