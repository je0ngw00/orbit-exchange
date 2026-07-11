# orbit-exchange 개발 단축 명령
# 로컬에 rpk/redis-cli/psql 설치 없이, 컨테이너 안의 도구를 docker exec로 사용한다.

COMPOSE = docker compose -f infra/docker-compose.yml

.PHONY: up down down-hard logs ps health psql redis topics

## 인프라 기동 (백그라운드)
up:
	$(COMPOSE) up -d

## 컨테이너 중지+삭제 (볼륨=데이터는 유지)
down:
	$(COMPOSE) down

## 컨테이너+볼륨까지 완전 삭제 (데이터 날림, 초기화용)
down-hard:
	$(COMPOSE) down -v

## 전체 로그 따라보기
logs:
	$(COMPOSE) logs -f

## 상태 확인 (healthy 여부)
ps:
	$(COMPOSE) ps

## Redpanda 클러스터 헬스
health:
	$(COMPOSE) exec redpanda rpk cluster health

## Postgres 접속 (psql)
psql:
	$(COMPOSE) exec postgres psql -U orbit -d orbit

## Redis 접속 (redis-cli)
redis:
	$(COMPOSE) exec redis redis-cli

## 토픽 목록
topics:
	$(COMPOSE) exec redpanda rpk topic list
