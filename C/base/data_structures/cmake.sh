rm -rf build
mkdir build

export LIBRARY_PATH=/usr/local/Cellar/check/0.13.0/lib

cd build
cmake ..
make
make test