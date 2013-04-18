package com.ruyicai.prizedata.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.prizedata.domain.PrizeInfo;

@Service("prizeInfoService")
@Transactional
public class PrizeInfoService {

	private static Logger logger = LoggerFactory
			.getLogger(PrizeInfoService.class);
	@PersistenceContext
	EntityManager em;

	public void persist(PrizeInfo prizeInfo) {
		em.persist(prizeInfo);
	}

	@Transactional(readOnly = true)
	public List<PrizeInfo> find(String lotno, int start, int step) {
		List<PrizeInfo> list = em
				.createQuery(
						"select o from PrizeInfo o where o.lotno=? order by batchcode desc",
						PrizeInfo.class).setParameter(1, lotno)
				.setFirstResult(start).setMaxResults(step).getResultList();
		return list;
	}

	public void merge(PrizeInfo prizeInfo) {
		List<PrizeInfo> list = em
				.createQuery(
						"select o from PrizeInfo o where o.lotno=? and o.batchcode=?",
						PrizeInfo.class).setParameter(1, prizeInfo.getLotno())
				.setParameter(2, prizeInfo.getBatchcode()).getResultList();

		if (!list.isEmpty()) {
			PrizeInfo p = list.get(0);
			prizeInfo.setId(p.getId());
		}
		prizeInfo.setCreatedate(new Date());
		em.merge(prizeInfo);

	}

	public int getIndex(String lotno, String batchcode) {
		int i = em
				.createQuery(
						"select o from PrizeInfo o where o.lotno=? and o.batchcode>?")
				.setParameter(1, lotno).setParameter(2, batchcode)
				.getResultList().size();
		return i;
	}

	public PrizeInfo findByLotnoAndBatchcode(String lotno, String batchcode) {
		
		TypedQuery<PrizeInfo> query = em.createQuery(
				"select o from PrizeInfo o where o.lotno=? and o.batchcode=?",
				PrizeInfo.class).setParameter(1, lotno).setParameter(2, batchcode);
		if(query.getResultList().isEmpty()) {
			return null;
		}
		
		return query.getSingleResult();
	}

}
