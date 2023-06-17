echo "starting run build docker";
docker build -t stc-project -f DockerFile .

echo "finished docker build";
echo "starting docker-compose";
docker-compose up;