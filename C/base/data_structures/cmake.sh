rm -rf build
mkdir build

cd build
cmake ..
make
ctest --ouput-on-failure .