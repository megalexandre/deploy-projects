#!/bin/bash

# Alternative: Run tests WITHOUT Testcontainers using Docker Compose
# This script starts a real PostgreSQL container and runs tests against it

set -e

echo "=========================================="
echo "🧪 Running Tests with Docker Compose"
echo "=========================================="
echo ""

# Check if docker-compose is available
if ! command -v docker-compose >/dev/null 2>&1; then
    echo "❌ docker-compose is not installed"
    exit 1
fi

# Start PostgreSQL container
echo "1️⃣ Starting PostgreSQL container..."
docker-compose up -d postgres 2>/dev/null || {
    echo "Starting PostgreSQL directly..."
    docker run -d \
        --name deploy-board-test-db \
        -e POSTGRES_DB=appdb \
        -e POSTGRES_USER=app \
        -e POSTGRES_PASSWORD=secret \
        -p 5432:5432 \
        postgres:18-alpine
}
echo "✅ PostgreSQL is running"
echo ""

# Wait for PostgreSQL to be ready
echo "2️⃣ Waiting for PostgreSQL to be ready..."
sleep 5

# Run tests with explicit database configuration
echo "3️⃣ Running tests..."
echo "=========================================="
echo ""

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/appdb \
SPRING_DATASOURCE_USERNAME=app \
SPRING_DATASOURCE_PASSWORD=secret \
./gradlew test --console=plain

echo ""
echo "=========================================="
echo "✅ Tests completed!"
echo "=========================================="
echo ""

# Cleanup
echo "4️⃣ Cleaning up..."
docker-compose down 2>/dev/null || docker stop deploy-board-test-db && docker rm deploy-board-test-db
echo "✅ Cleanup complete"
echo ""

echo "📊 View test report at:"
echo "  file://$(pwd)/build/reports/tests/test/index.html"

