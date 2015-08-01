# Displays a customers bill

## Overview

This will display a customers sky bill.

## Acceptance behaviour

**In order to** check my bill  
**As a** Customer  
**I want to** see all information about my bill  

---

**Scenario 1**: View their overall statement  
**Given:** 'They are an existing customer'  
**When:** 'They view their statement'  
**Then:** 'The statement period from and to date are displayed'  
**And:** 'The statement due date is displayed'  
**And:** 'The statement total cost is displayed'  
**And:** 'The call charge total cost is displayed'  
**And:** 'The package total cost is displayed'  
**And:** 'The sky store purchase total cost is displayed'  

**Scenario 2**: View their current package details  
**Given:** 'They are an existing customer'  
**When:** 'They view their statement'  
**And:** 'They want to see their current package subscriptions'  
**Then:** 'The type, name and cost is displayed for all package subscriptions'  

**Scenario 3**: View their call charges in the statement period  
**Given:** 'They are an existing customer'  
**When:** 'They view their statement'  
**And:** 'They want to see all their call charges'  
**Then:** 'All the calls made during the statement period are displayed'  
**And:** 'The call number, the duration and the cost are displayed for all calls'  

**Scenario 4**: View all of their store purchases made in the statement period      
**Given:** 'They are an existing customer'  
**When:** 'They view their statement'  
**And:** 'They want to see all their store purchases'  
**Then:** 'All the rentals made during the statement period are displayed'  
**And:** 'The rental name and cost is displayed for each rental'  
**And:** 'All of the buy and keep purchases made during the statement period are displayed'  
**And:** 'The name and cost is displayed for each but and keep purchase'    
