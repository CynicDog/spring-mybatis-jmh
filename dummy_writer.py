import random

def dummy(id: int, job: str, manager: int, dept: int):
    salary = round(random.uniform(3000.00, 10000.00), 2)  # Random salary between 3000 and 10000
    commission_pct = round(random.uniform(0.0, 0.5), 2)  # Random commission percentage between 0 and 0.5

    # Randomize names and email
    first_name = random.choice(["John", "Jane", "Robert", "Linda", "David", "Karen", "Claire", "David", "Sophie", "Clara", "Dwight", "Bobby", "Jaden", "Marie"])
    last_name = random.choice(["Smith", "Johnson", "Williams", "Brown", "Jones", "Turner", "Grant"])
    email = first_name.lower() + "@example.com"

    # Randomize phone number
    phone = f"{random.randint(100, 999)}-{random.randint(1000, 9999)}"

    # Randomize date
    year = random.randint(2000, 2023)
    month = random.randint(1, 12)
    day = random.randint(1, 28)
    date = f"{year}-{month:02d}-{day:02d}"

    return f"({id},'{first_name}','{last_name}','{email}','{phone}','{date}','{job}',{salary},{commission_pct},{manager},{dept})"

result = []
for i in range(1, 5000):
    result.append(dummy(i, "ST_CLERK", 121, 50))

insert_statement = "INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)\nVALUES\n"

with open('population.txt', 'w') as f:
    f.write(insert_statement + ",\n".join(result) + ";")
