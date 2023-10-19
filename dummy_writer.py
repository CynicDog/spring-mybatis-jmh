import random
import uuid

def dummy(job: str, manager: int, dept: int):
    salary = round(random.uniform(3000.00, 10000.00), 2)  # Random salary between 3000 and 10000
    commission_pct = round(random.uniform(0.0, 0.5), 2)  # Random commission percentage between 0 and 0.5

    # Randomize names and email
    first_name = random.choice(["John", "Jane", "Robert", "Linda", "David", "Karen", "Claire", "David", "Sophie", "Clara", "Dwight", "Bobby", "Jaden", "Marie"])
    last_name = random.choice(["Smith", "Johnson", "Williams", "Brown", "Jones", "Turner", "Grant"])

    unique_id = str(uuid.uuid4())
    email = first_name.lower() + f"-{unique_id}@example.com"

    # Randomize phone number
    phone = f"{random.randint(100, 999)}-{random.randint(1000, 9999)}"

    # Randomize date
    year = random.randint(2000, 2023)
    month = random.randint(1, 12)
    day = random.randint(1, 28)
    date = f"{year}-{month:02d}-{day:02d}"

    return f"('{first_name}','{last_name}','{email}','{phone}','{date}','{job}',{salary},{commission_pct},{manager},{dept})"

job_manager_dept = [
    ("IT_PROG", 102, 60),
    ("IT_PROG", 103, 60),
    ("FI_MGR", 101, 100),
    ("FI_ACCOUNT", 108, 100),
    ("PU_MAN", 100, 30),
    ("PU_CLERK", 114, 30),
    ("ST_MAN", 100, 50),
    ("ST_CLERK", 120, 50),
    ("ST_CLERK", 121, 50),
    ("ST_CLERK", 122, 50),
    ("ST_CLERK", 123, 50),
    ("ST_CLERK", 124, 50),
    ("SA_MAN", 100, 80),
    ("SA_REP", 145, 80),
    ("SA_REP", 146, 80),
    ("SA_REP", 147, 80),
    ("SA_REP", 148, 80),
    ("SA_REP", 149, 80),
    ("SH_CLERK", 120, 50),
    ("SH_CLERK", 121, 50),
    ("SH_CLERK", 122, 50),
    ("SH_CLERK", 123, 50),
    ("SH_CLERK", 124, 50),
    ("AD_ASST", 101, 10),
    ("MK_MAN", 100, 20),
    ("MK_REP", 201, 20),
    ("HR_REP", 101, 40),
    ("PR_REP", 101, 70),
    ("AC_MGR", 101, 110),
    ("AC_ACCOUNT", 205, 110),
]

result = []
for i in range(500, 25500):
    job, manager, dept = random.choice(job_manager_dept)
    result.append(dummy(job, manager, dept))

insert_statement = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)\nVALUES\n"

with open('population.txt', 'w') as f:
    f.write(insert_statement + ",\n".join(result) + ";")
