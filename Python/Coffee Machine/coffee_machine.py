
def check_resources(resources, coffee):
    for i in range(len(resources)):
        if resources[i] - coffee[i] < 0:
            if i == 0:
                print("Sorry, not enough water!")
            elif i == 1:
                print("Sorry, not enough milk!")
            elif i == 2:
                print("Sorry, not enough coffee beans!")
            elif i == 3:
                print("Sorry, not enough disposable cups!")
            return False
    return True

def buy(resources, type):
    print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    choice = input()
    if choice == "back":
        return resources

    if check_resources(resources, type[int(choice) - 1]):
        print("I have enough resources, making you a coffee!")
    else:
        return resources

    if choice == "1":
        for i in range(len(resources)):
            resources[i] -= type[0][i]
    elif choice == "2":
        for i in range(len(resources)):
            resources[i] -= type[1][i]
    elif choice == "3":
        for i in range(len(resources)):
            resources[i] -= type[2][i]
    return resources

def fill(resources):
    print("Write how many ml of water you want to add:")
    resources[0] += int(input())
    print("Write how many ml of milk you want to add: ")
    resources[1] += int(input())
    print("Write how many grams of coffee beans you want to add:")
    resources[2] += int(input())
    print("Write how many disposable cups of coffee you want to add: ")
    resources[3] += int(input())
    return resources

def take(resources):
    print("I gave you $" + str(resources[4]))
    resources[4] = 0
    return resources

def remaining(resources):
    print("The coffee machine has:")
    print(resources[0], "ml of water")
    print(resources[1], "ml of milk")
    print(resources[2], "g of coffee beans")
    print(resources[3], "disposable cups")
    print("$" + str(resources[4]), "of money")

def main():
    espresso = [250, 0, 16, 1, -4]
    latte = [350, 75, 20, 1, -7]
    cappuccino = [200, 100, 12, 1, -6]
    type = [espresso, latte, cappuccino]
    resources = [400, 540, 120, 9, 550]
    while True:
        print("Write action (buy, fill, take, remaining, exit):")
        action = input()
        if action == "buy":
            resources = buy(resources, type)
        elif action == "fill":
            resources = fill(resources)
        elif action == "take":
            resources = take(resources)
        elif action == "remaining":
            remaining(resources)
        elif action == "exit":
            exit(1)

main()