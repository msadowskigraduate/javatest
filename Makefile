package:
	@echo "Running Maven Package..."
	mvn package
build_docker_image:
	@echo "Building Docker image..."
	docker build . --tag javatest:latest
docker_compose_up:
	@echo "Docker Compose up!"
	docker-compose up