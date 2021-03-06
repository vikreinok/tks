package ee.ttu.catering.rest.repository;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.Diner_;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.Menu_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.*;

public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu>{
	
	public static class Specs {
		public static Specification<Menu> findMenusByDinerId(final int dinerId) {
			return new Specification<Menu>() {

				@Override
	            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                Join<Menu, Diner> diners = root.join(Menu_.diner);
	                return cb.equal(diners.get(Diner_.id), dinerId);
	            }
			};
		}
	}


}
