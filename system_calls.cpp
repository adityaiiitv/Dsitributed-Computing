#include <iostream>
#include <sys/file.h>
#include <unistd.h>
#include <dirent.h>
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
using namespace std;
int foo(int fd, char* buf, int b_size, int n, int skip)
{
	int flag=0;size_t sum=0;
	while(flag==0)
	{
		if(n==0)
		{
			flag=1;
		}
		else
		{
			n--;
			for(int i=0;i<b_size;i++)
			{
				if(fd)
				{
					sum = sum + read(fd,buf, 1);
				}
				else
				{
					flag=1;
					break;
				}
			}
			for(int i=0;i<skip;i++)
			{
				if(fd)
				{
					lseek(fd,1,1);
				}
			}
		}
	}
	return sum;
}
int main(){
	FILE *fp = fopen("test","r");
	char *buf[5];
	foo(fp,buf,4,8,6);
	return 0;
}
