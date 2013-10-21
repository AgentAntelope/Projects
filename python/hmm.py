import re

def find_head_section(file_data, header):
        line_pos = 0
        i = 0
        for line in file_data:
            if line.startswith(header):
                line_pos = i
                break
            i += 1
        return line_pos

class HiddenMarkovFactory:
    @staticmethod
    def produce(file_name):
        f = open(file_name, 'r')
        lines = [x.rstrip() for x in f]
        alphabet = HiddenMarkovFactory.produce_alphabet(lines)
        states = HiddenMarkovFactory.produce_state(lines)
        transition_probability = HiddenMarkovFactory.produce_transitions(lines, len(states))
        emission_probability = HiddenMarkovFactory.produce_emission_probability(lines, len(states), len(alphabet))
        start_probability = HiddenMarkovFactory.produce_start(lines)
        return HiddenMarkovModel(alphabet, states, transition_probability, emission_probability, start_probability)
        
        
    @staticmethod
    def produce_alphabet(file_data):
        line_pos = find_head_section(file_data, "Alphabet")
        alphabet_amount = int(file_data[line_pos + 1])
        alphabet = file_data[line_pos + 2].split('\t')
        return alphabet
        

    @staticmethod
    def produce_state(file_data):
        line_pos = find_head_section(file_data, "States")
        state = file_data[line_pos + 2].split('\t')
        return state

    @staticmethod
    def produce_transitions(file_data, state_count):
        line_pos = find_head_section(file_data, "TransitionProbability")
        transitions = [[0.0 for x in range(state_count)] for x in range(state_count)]
        for state in range(state_count):
            line = file_data[line_pos + state + 1]
            pos = 0
            for item in line.split('\t'):
                transitions[state][pos] = float(item)
                pos += 1
        return transitions
        
    @staticmethod
    def produce_emission_probability(file_data, state_count, alphabet_count):
        line_pos = find_head_section(file_data, "EmissionProbability")
        emission = [[0.0 for x in range(alphabet_count)] for x in range(state_count)]
        for state in range(state_count):
            line = file_data[line_pos + state + 1]
            pos = 0
            for item in line.split('\t'):
                emission[state][pos] = float(item)
                pos += 1
        return emission

    @staticmethod
    def produce_start(file_data):
        line_pos = find_head_section(file_data, "StartProbability")
        start_probabilities = file_data[line_pos + 1].split('\t')
        start_probabilities = [float(x) for x in start_probabilities]
        return start_probabilities
    


class HiddenMarkovModel:
    def __init__(self, alphabet, states, transition_probability, emission_probability, start_probability):
        self.alphabet = alphabet
        self.states = states
        self.transition_probability = transition_probability
        self.emission_probability = emission_probability
        self.start_probability = start_probability

    def __str__(self):
        st = 'Alphabet: '
        for letter in self.alphabet:
            st += letter + ' '
        
        st += '\nStates: '
        states_str = '-'.join(self.states)
        st += states_str
        st += '\nTransition probabilities: ' + str(self.transition_probability)
        st += '\nEmission probabilities: ' + str(self.emission_probability)
        st += '\nStart Probabilities: ' + str(self.start_probability)
        
        return st

print HiddenMarkovFactory.produce("markov.txt")
