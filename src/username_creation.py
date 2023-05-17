import random

random_list = []


class Username :
    def randN(self,N):
        min = pow(10, N - 1)
        max = pow(10, N) - 1
        return random.randint(min, max)


    def create_username(self,usertype, name, ):
        username = ""
        random_num = self.randN(4)
        unique_rnum = 0
        if random_num not in random_list:
            unique_rnum = random_num
            random_list.append(unique_rnum)
            unique_rnum = str(unique_rnum)
        if usertype == "STUDENT":
            username = "BLS" + unique_rnum + name[0] + name[-1]
        elif usertype == "PARENT":
            username = "BLP" + unique_rnum + name[0] + name[-1]
        elif usertype == "ORG":
            username = "BLO" + unique_rnum + name[0] + name[-1]

        return username


user_class = Username()
username_org = user_class.create_username("ORG", "APPLE")
username_parent = user_class.create_username("PARENT", "RAJIV")
username_student = user_class.create_username("STUDENT", "ANAND")

print(username_org)
print(username_parent)
print(username_student)
