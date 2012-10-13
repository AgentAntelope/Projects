succDict = {'buch': ['pit', 'faga', 'urz', 'giu'], 'cra': ['dob', 'rim', 'pit'], 'hir': ['efo', 'urz'], 'tim': ['arad', 'lug'], 'dob': ['cra', 'meh'], 'giu': ['buch'], 'efo': ['hir'], 'meh': ['dob', 'lug'], 'nea': ['ias'], 'zer': ['arad', 'ora'], 'sib': ['arad', 'ora', 'faga', 'rim'], 'rim': ['sib', 'pit', 'cra'], 'ias': ['nea', 'vas'], 'arad': ['zer', 'sib', 'tim'], 'lug': ['meh', 'tim'], 'vas': ['urz', 'ias'], 'faga': ['sib', 'buch'], 'pit': ['rim', 'cra', 'buch'], 'urz': ['buch', 'hir', 'vas'], 'ora': ['zer', 'sib']}

hDict = {'buch': 0, 'sib': 253, 'dob': 242, 'giu': 77, 'lug': 244, 'rim': 193, 'hir': 151, 'nea': 234, 'zer': 374, 'tim': 329, 'meh': 241, 'cra': 160, 'urz': 80, 'arad': 366, 'efo': 161, 'vas': 199, 'ora': 380, 'pit': 100, 'ias': 226, 'faga': 176}


def crows_nest_jump_heuristic(current_city_node):
	current_city_str = current_city_node.state
	crows_dist = hDict[current_city_str]
	connected_cities = succDict[current_city_str]
	for connected_city in connected_cities:
		crows_dist = min(crows_dist, hDict[connected_city])
	current_city_node.hval = crows_dist
	return crows_dist