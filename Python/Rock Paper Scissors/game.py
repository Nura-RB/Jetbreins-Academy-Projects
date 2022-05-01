import random

allOptions = ['scissors', 'fire', 'rock', 'hun', 'lightning', 'devil', 'dragon', 'water', 'gun',
              'snake', 'human', 'tree', 'wolf', 'sponge', 'paper', 'air']

winning_cases = {
    'water': ['scissors', 'fire', 'rock', 'gun', 'lightning', 'devil', 'dragon'],
    'dragon': ['snake', 'scissors', 'fire', 'rock', 'gun', 'lightning', 'devil'],
    'devil': ['tree', 'human', 'snake', 'scissors', 'fire', 'rock', 'gun'],
    'gun': ['wolf', 'tree', 'human', 'snake', 'scissors', 'fire', 'rock'],
    'rock': ['sponge', 'wolf', 'tree', 'human', 'snake', 'scissors', 'fire'],
    'fire': ['paper', 'sponge', 'wolf', 'tree', 'human', 'snake', 'scissors'],
    'scissors': ['air', 'paper', 'sponge', 'wolf', 'tree', 'human', 'snake'],
    'snake': ['water', 'air', 'paper', 'sponge', 'wolf', 'tree', 'human'],
    'human': ['dragon', 'water', 'air', 'paper', 'sponge', 'wolf', 'tree'],
    'tree': ['devil', 'dragon', 'water', 'air', 'paper', 'sponge', 'wolf'],
    'wolf': ['lightning', 'devil', 'dragon', 'water', 'air', 'paper', 'sponge'],
    'sponge': ['gun', 'lightning', 'devil', 'dragon', 'water', 'air', 'paper'],
    'paper': ['rock', 'gun', 'lightning', 'devil', 'dragon', 'water', 'air'],
    'air': ['fire', 'rock', 'gun', 'lightning', 'devil', 'dragon', 'water'],
    'lightning': ['tree', 'human', 'snake', 'scissors', 'fire', 'rock', 'gun']
}


def choose():
    arr = []
    str = input()
    if str == '':
        arr.append('rock')
        arr.append('paper')
        arr.append('scissors')
    else:
        arr = str.split(',')
    return arr


def cycle(rating, options, hang):
    a = random.randint(0, len(options) - 1)
    if hang == options[a]:
        rating += 50
        print("There is a draw (", options[a],")")
        return rating
    for elem in winning_cases[options[a]]:
        if hang == elem:
            print("Sorry, but the computer chose", options[a])
            return rating
    print("Well done. The computer chose", options[a], "and failed")
    rating += 100
    return rating


def show_rating(rating):
    print("Your rating:", rating)

# ------------------------ main ------------------------------------------

def main():
    print("Enter your name:")
    name = input()
    print("Hello,", name)
    arrOfOpt = choose()
    print("Okay, let's start")
    fileN = open("rating.txt", 'r')

    rating = ""
    for line in fileN:
        if name in line:
            for ch in line:
                if ch.isdigit():
                    rating += ch

    rating = int(rating)
    while True:
        hang = input()
        if hang == "!rating":
            show_rating(rating)
        elif hang == "!exit":
            print("Bye!")
            fileN.close()
            exit(1)
        elif hang in arrOfOpt:
            rating = cycle(rating, arrOfOpt, hang)
        else:
            print("Invalid input")

main()
