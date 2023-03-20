Feature: SanityTest
  I want to perform Sanity Test on all unity environments

  @quickSanity
  Scenario: 33844040|Sanity Test QC191
    Given user is on Unity Login Page
    When user logs into Unity with "#UserID"
   # Given user is on Instruction page
   # When add details about instruction
   Instruction Title | Instruction Descirption
   |T1| TD | abc  ---------------
   # Then Instruction should get created
   # Then User creates category
   # Then User should add questions
   Q1
   Q2
   Q3
    Then User should add questionnaire
    
    @quickSanity
  Scenario: 33844040|Sanity Test QC191
    Given user is on Unity Login Page
    When user logs into Unity with "#UserID"
   # Given user is on Instruction page
   # When add details about instruction
   Instruction Title | Instruction Descirption
   |T2| Tr
   # Then Instruction should get created
   # Then User creates category
   # Then User should add questions
   Q4
   Q5
    Then User should add questionnaire