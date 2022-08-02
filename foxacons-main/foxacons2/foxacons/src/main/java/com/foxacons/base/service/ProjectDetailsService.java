package com.foxacons.base.service;

import java.util.List;

import com.foxacons.base.entity.ProjectDetails;

public interface ProjectDetailsService {
	public List<ProjectDetails> getAllProjects();

	public boolean addProject(ProjectDetails details);

	public boolean deleteProject(int id);

	public boolean updateProject(ProjectDetails details);
}
