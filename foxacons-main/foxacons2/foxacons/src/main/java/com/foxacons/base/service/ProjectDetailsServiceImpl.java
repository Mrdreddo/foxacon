package com.foxacons.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxacons.base.entity.ProjectDetails;
import com.foxacons.base.repos.ProjectDetailsDao;

@Service
public class ProjectDetailsServiceImpl  implements ProjectDetailsService{
	@Autowired
	private ProjectDetailsDao dao;

	@Override
	public List<ProjectDetails> getAllProjects() {
		List<ProjectDetails> details =(List<ProjectDetails>) dao.findAll();
		if(details.size()>=0) {
			return details;
		}
		return null;
	}

	@Override
	public boolean addProject(ProjectDetails details) {
		boolean isAdded=false;
		if(details!=null) {
			ProjectDetails details2= dao.save(details);
			if(details2!=null) {
				isAdded=true;
			}
		}
		return isAdded;
	}

	@Override
	public boolean deleteProject(int id) {
		boolean isDeleted=false;
		ProjectDetails details=dao.findByProjectId(id);
		if(details!=null) {
			dao.delete(details);
			isDeleted=true;
		}
		return isDeleted;
	}

	@Override
	public boolean updateProject(ProjectDetails details) {
		
		return false;
	}

}
