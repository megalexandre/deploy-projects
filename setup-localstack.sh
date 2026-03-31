#!/bin/bash

# Script para configurar LocalStack S3 para desenvolvimento local
# Uso: ./setup-localstack.sh

set -e

echo "🚀 Configurando LocalStack S3..."

# Verificar se Docker está rodando
if ! docker info > /dev/null 2>&1; then
    echo "❌ Erro: Docker não está rodando"
    exit 1
fi

# Verificar se AWS CLI está instalado
if ! command -v aws &> /dev/null; then
    echo "❌ Erro: AWS CLI não está instalado"
    echo "Instale com: pip install awscli-local"
    exit 1
fi

# Endpoint do LocalStack
ENDPOINT="http://localhost:4566"
BUCKET_NAME="deploy-board-uploads"

echo "📦 Verificando se LocalStack está rodando..."
if ! curl -s $ENDPOINT/_localstack/health > /dev/null; then
    echo "⚠️  LocalStack não está rodando. Iniciando..."
    docker-compose up -d localstack
    echo "⏳ Aguardando LocalStack inicializar..."
    sleep 5
fi

echo "✅ LocalStack está rodando"

# Criar bucket se não existir
echo "🪣 Criando bucket S3: $BUCKET_NAME"
aws --endpoint-url=$ENDPOINT s3 mb s3://$BUCKET_NAME 2>/dev/null || echo "Bucket já existe"

# Listar buckets
echo "📋 Buckets disponíveis:"
aws --endpoint-url=$ENDPOINT s3 ls

# Configurar CORS (opcional)
echo "🔧 Configurando CORS..."
cat > /tmp/cors-config.json <<EOF
{
  "CORSRules": [
    {
      "AllowedOrigins": ["*"],
      "AllowedMethods": ["GET", "PUT", "POST", "DELETE"],
      "AllowedHeaders": ["*"],
      "ExposeHeaders": ["ETag"]
    }
  ]
}
EOF

aws --endpoint-url=$ENDPOINT s3api put-bucket-cors \
    --bucket $BUCKET_NAME \
    --cors-configuration file:///tmp/cors-config.json 2>/dev/null || echo "CORS já configurado"

rm /tmp/cors-config.json

echo ""
echo "✅ Configuração concluída!"
echo ""
echo "📝 Configurações para usar:"
echo "   AWS_S3_ENDPOINT=http://localhost:4566"
echo "   AWS_S3_ACCESS_KEY=test"
echo "   AWS_S3_SECRET_KEY=test"
echo "   AWS_S3_BUCKET_NAME=$BUCKET_NAME"
echo "   AWS_S3_REGION=us-east-1"
echo ""
echo "🚀 Inicie a aplicação com:"
echo "   ./gradlew bootRun --args='--spring.profiles.active=local,local-s3'"
echo ""
echo "🔍 Comandos úteis:"
echo "   aws --endpoint-url=$ENDPOINT s3 ls s3://$BUCKET_NAME --recursive"
echo "   aws --endpoint-url=$ENDPOINT s3 cp s3://$BUCKET_NAME/path/file.pdf ./file.pdf"
echo ""

