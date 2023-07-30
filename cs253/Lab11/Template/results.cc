#include <iomanip>	// for right, setw
#include <iostream>
#include <map>
#include <string>

using namespace std;

template <typename T>
class BarGraph {
  public:
    void operator+=(const T &datum) {
        data[datum]++;
    }
    friend ostream &operator<<(ostream &out, const BarGraph<T> &b){
	for (const auto &val : b.data)
	    out  << right << setw(10) << val.first << ' '
		 << string(val.second, '*') << '\n';
        out << '\n';
	return out;
    }
  private:
    map<T, unsigned> data;
};

template <>
class BarGraph<bool> {
    unsigned false_count = 0, true_count = 0;
  public:
    void operator+=(bool datum) {
	datum ? true_count++ : false_count++;
    }
    friend ostream &operator<<(ostream &out, const BarGraph<bool> &b){
        cout << "     false " << string(b.false_count, '*') << "\n"
                "      true " << string(b.true_count,  '*') << "\n\n";
	return out;
    }
};

template <>
class BarGraph<char> {
public:
	void operator+=(string c){
		s=c;
		for(char p : s){
			data[int(p)]++;
		}
	}
	void operator+=(const char p){
		data[int(p)]++;
	}
	friend ostream &operator<<(ostream &out, const BarGraph<char> &c){
		for(int i=0;i<200;i++){
			if(c.data[i] != 0){
				out  << right << setw(10) << char(i) << ' '
                             	     << string(c.data[i], '*') << '\n';
			}
		}
		out << '\n';
		return out;
	}
private:
	string s;
	int data[200]={};
};

int main() {
    BarGraph<int> alpha;
    alpha += 12;
    alpha += 6;
    alpha += 4;
    alpha += 6;
    cout << alpha;

    BarGraph<double> beta;
    beta += 3.14;
    beta += 2.71828;
    beta += 6.023e23;
    beta += 2.71828;
    cout << beta;

    BarGraph<bool> gamma;
    gamma += false;
    gamma += true;
    gamma += false;
    gamma += true;
    gamma += true;
    cout << gamma;

    BarGraph<char> delta;
    delta += 'G';
    delta += 'e';
    delta += 'o';
    delta += 'f';
    delta += 'f';
    delta += "Colorado";
    cout << delta;

    return 0;
}
